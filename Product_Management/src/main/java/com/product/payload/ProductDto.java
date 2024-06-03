package com.product.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

	private Long sId;

	private Long pId;

	@NotEmpty
	@Size(min = 3, message = "name must be 3 character")
	private String pName;

	private Long pPrice;
	
	@NotEmpty
	@Size(min = 3, message = "category must be 3 character")
	private String pCategory;

	private String pDescription;
}
