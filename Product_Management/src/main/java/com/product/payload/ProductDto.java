package com.product.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductDto {

	private Long sId;

	private Long pId;

	@NotEmpty
	@Size(min = 3, message = "name must be 3 character")
	private String pName;

	private String pImage;

	private Long pPrice;

	@NotEmpty
	@Size(min = 3, message = "category must be 3 character")
	private String pCategory;

	private String pDescription;

	public Long getsId() {
		return sId;
	}

	public void setsId(Long sId) {
		this.sId = sId;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpImage() {
		return pImage;
	}

	public void setpImage(String pImage) {
		this.pImage = pImage;
	}

	public Long getpPrice() {
		return pPrice;
	}

	public void setpPrice(Long pPrice) {
		this.pPrice = pPrice;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	public String getpDescription() {
		return pDescription;
	}

	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

}
