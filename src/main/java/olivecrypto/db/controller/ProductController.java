package olivecrypto.db.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import olivecrypto.db.entity.Product;
import olivecrypto.db.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
    @Autowired
	private ProductService service;
    Logger log = LoggerFactory.getLogger("ProductController.class");
    @PostMapping("/addProduct")
    public DeferredResult<Product> addProduct(@RequestBody Product product, @RequestParam(required=false, defaultValue = "5000") Integer timeout) {
    	log.info("/addProduct url called - INFO");
    	DeferredResult<Product> dr= new DeferredResult<Product>(10000L,new Product());
    	service.saveProduct(dr,product,timeout);
    	return dr;
    }
    
    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products){
    	log.info("/addProducts url called - INFO");
    	return service.saveProducts(products);
    }
    
    @GetMapping("/products")
    public List<Product> findAllProducts(){
    	log.info("/products url called - INFO");
    	return service.getProducts();
    }
    
	
	  @GetMapping("/product/{id}") public Product findProductById(@PathVariable
	  Integer id) { log.info("/product/{id} url called - INFO"); return
	  service.getProductById(id); }
	 
    
    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
    	log.info("/update url called - INFO");
    	return service.updateProduct(product);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
    	log.warn("/delete/{id} url called - INFO");
    	return service.deleteProduct(id);
    }
    
    @GetMapping("/search/{name}")
    public List<Product> searchProduct(@PathVariable String name) {
    	log.info("/search/{name} url called - INFO");
		return service.search(name);
    }
}
