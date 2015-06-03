package org.libventary;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.jpa.JpaEventStore;
import org.axonframework.saga.GenericSagaFactory;
import org.axonframework.saga.SagaFactory;
import org.axonframework.saga.SagaManager;
import org.axonframework.saga.SagaRepository;
import org.axonframework.saga.annotation.AnnotatedSagaManager;
import org.axonframework.saga.repository.jpa.JpaSagaRepository;
import org.axonframework.saga.spring.SpringResourceInjector;
import org.axonframework.unitofwork.SpringTransactionManager;
import org.libventary.model.book.Book;
import org.libventary.model.librarycard.LibraryCard;
import org.libventary.model.onlinereservation.OnlineReservationSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class AxonConfig {

	@Value("${eventStoreLocation:./events-store}")
	private String	eventStoreLocation;

	@Autowired
	PlatformTransactionManager platformTransactionManager;
	
	
	@Bean
	public CommandBus commandBus() {
		SimpleCommandBus cmdBus = new SimpleCommandBus();
		cmdBus.setTransactionManager(axonTransactionManager());
		return cmdBus;
	}

	@Bean
	public SpringTransactionManager axonTransactionManager() {
        return new SpringTransactionManager(platformTransactionManager);
    }

    @Bean
	public CommandGateway commandGateway() {
		return new DefaultCommandGateway(commandBus());
	}

	@Bean
	public EventBus eventBus() {
		EventBus eventBus = new SimpleEventBus();
		eventBus.subscribe(sagaManager());
		return eventBus;
	}

	@Bean
	public EventStore eventStore() {
		return new JpaEventStore(entityManagerProvider());
	}
	
	@Bean
	public ContainerManagedEntityManagerProvider entityManagerProvider() {
	    return new ContainerManagedEntityManagerProvider();
	}

	@Bean
	public SagaRepository sagaRepository() {
	    JpaSagaRepository repo = new JpaSagaRepository(entityManagerProvider());
	    repo.setResourceInjector(springResourceInjector());
	    return repo;
	}
	
	@Bean
	public SpringResourceInjector springResourceInjector() {
	    return new SpringResourceInjector();
	}
	
	@Bean
	public SagaManager sagaManager() {
	    return new AnnotatedSagaManager(sagaRepository(), sagaFactory(), OnlineReservationSaga.class);
	}
	
	@Bean
	public SagaFactory sagaFactory() {
        GenericSagaFactory factory = new GenericSagaFactory();
        factory.setResourceInjector(springResourceInjector());
        return factory;
    }

    /*
	 * This bean automatically generates CommandHandlerAdapter for each bean containing @CommandHandler annotation.
	 * 
	 * Same as if you set it manually per each handler with: AnnotationCommandHandlerAdapter.subscribe(new
	 * OrganizationActivationCommandHandler(), commandBus);
	 */
	@Bean
	public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
		AnnotationCommandHandlerBeanPostProcessor processor = new AnnotationCommandHandlerBeanPostProcessor();
		processor.setCommandBus(commandBus());
		return processor;
	}

	/*
	 * This bean automatically generates EventHandlerAdapter for each bean containing @EventHandler annotation.
	 * 
	 * Same as if you set it manually per each handler with: AnnotationEventListenerAdapter.subscribe(new
	 * OrganizationActivationEventHandler(), eventBus);
	 */
	@Bean
	public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
		AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
		processor.setEventBus(eventBus());
		return processor;
	}

	// Maybe there is a way to automatically generate EventSourcingRepositories by annotations too?

	@Bean
    public EventSourcingRepository<LibraryCard> libraryCardRepository() {
        EventSourcingRepository<LibraryCard> repository = new EventSourcingRepository<>(LibraryCard.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }
	
	@Bean
    public EventSourcingRepository<Book> bookCardRepository() {
        EventSourcingRepository<Book> repository = new EventSourcingRepository<>(Book.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

}
