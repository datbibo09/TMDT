package com.ecommerce.product.infrastructure.messaging;

import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.model.ProductEvent;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class ProductEventProducer {

    @Channel("product-event")
    Emitter<ProductEvent> emitter;

    public void publish(Product product, ProductEvent.EventType type){
        ProductEvent event = new ProductEvent(type, product);
        emitter.send(event)
                .whenComplete((ok, ex) -> {
                    if(ex != null){
                        System.out.println("Failed to send product event: " + ex.getMessage());
                    }
                });
    }
}

