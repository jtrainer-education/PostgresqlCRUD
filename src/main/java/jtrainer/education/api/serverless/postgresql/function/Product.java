package jtrainer.education.api.serverless.postgresql.function;

import com.google.gson.*;

public class Product {
		 
	    private int id;
	    private Integer product_type_id;
	    private String ean;
	    private String name;
	 
	    public Product(String json) {
	        Gson gson = new Gson();
	        Product product = gson.fromJson(json, Product.class);
	        this.id = product.getId();
	        this.name = product.getName();
	        this.ean=product.getEan();
	        this.product_type_id=product.getProduct_type();
	    }
	 
	    public String toString() {
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        return gson.toJson(this);
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getProduct_type() {
			return product_type_id;
		}

		public void setProduct_type(Integer product_type) {
			this.product_type_id = product_type;
		}

		public String getEan() {
			return ean;
		}

		public void setEan(String ean) {
			this.ean = ean;
		}
	 
	    
	}

