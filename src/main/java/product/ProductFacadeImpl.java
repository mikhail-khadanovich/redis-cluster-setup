package product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {
    private final ProductService service;


    @Override
    public void saveProduct() {
        service.saveProduct();
    }
}
