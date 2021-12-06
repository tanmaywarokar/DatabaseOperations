package olivecrypto.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import olivecrypto.db.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value="select * from TBL_PRODUCT where product_name=:name", nativeQuery = true)
	public List<Product> search(@Param(value = "name") String name);
}
