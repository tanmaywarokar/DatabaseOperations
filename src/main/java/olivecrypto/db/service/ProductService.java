package olivecrypto.db.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import olivecrypto.db.entity.Product;
import olivecrypto.db.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
	private ProductRepository repo;
    
    Logger log = LoggerFactory.getLogger("ProductService.class");
    
    public Product saveProduct(Product product) {
    	try {
        	log.info("Thread Sleep for 12000 -INFO");
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	return repo.save(product);
    }
    
    public List<Product> saveProducts(List<Product> products){
    	log.info("saveProducts called -INFO");
    	return repo.saveAll(products);
    }
    
    public List<Product> getProducts(){
    	log.info("getProducts called -INFO");
    	return repo.findAll();
    }
    
    public Product getProductById(Integer id) {
    	log.info("getProductById called -INFO");
    	return repo.findById(id).orElse(null);
    }
    
    public String deleteProduct(Integer id) {
    	log.info("deleteById called -INFO");
    	repo.deleteById(id);
    	return "Product with id:" + id + " deleted successfully !!!";
    }
    
    public Product updateProduct(Product product) {
    	log.info("updateProduct called -INFO");
    	Product existingProduct = repo.findById(product.getId()).orElse(null);
    	existingProduct.setName(product.getName());
    	existingProduct.setQuantity(product.getQuantity());
    	existingProduct.setPrice(product.getPrice());
    	return repo.save(existingProduct);
    }
    
    public List<Product> search(String name) {
    	log.info("search called -INFO");
		return repo.search(name);    	
  }

    @Async
	public void saveProduct(DeferredResult<Product> dr, Product product, Integer timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	dr.setResult(repo.save(product));    	
	}
}
