package product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductFacade facade;

    @PostMapping(path = "/products")
    public void saveProduct() {
        facade.saveProduct();
    }

}
