package com.example.forum.Representation;

import com.example.forum.Tables.CustomCategory;

public class CustomCategoryRepresentation {
   private Long categoryId;
   
   private String categoryName;

   private String categoryDescription;

public Long getCategoryId() {
    return categoryId;
}

public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
}

public String getCategoryName() {
    return categoryName;
}

public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
}

public String getCategoryDescription() {
    return categoryDescription;
}

public void setCategoryDescription(String categoryDescription) {
    this.categoryDescription = categoryDescription;
}

public CustomCategoryRepresentation(CustomCategory category){
    setCategoryDescription(category.getCategoryDescription());
    setCategoryName(category.getCategoryName());
    setCategoryId(category.getCategoryId());
}

}
