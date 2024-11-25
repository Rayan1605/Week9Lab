package ie.atu.week8.projectexercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

@BeforeEach
    void setup(){

}

@Test
    void testSaveProductForApplyDiscount(){
    Product product = new Product(1L, "Laptop", "Expensive", 1500);
    when(productRepository.save(product)).thenReturn(product);
    Product expectedProduct = productService.saveProduct(product);
    assertEquals(1350, expectedProduct.getPrice());
}

  @Test
  void testValidateProductForIllegalArgument()
  {
      Product product = new Product(1L, "Laptop", "Expensive", -1500);
      IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()-> productService.saveProduct(product));
      Product expectedProduct = productService.saveProduct(product);
      assertEquals("price cannot be negative", iae.getMessage());
  }


}
