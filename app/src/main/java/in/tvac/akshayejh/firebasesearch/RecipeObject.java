package in.tvac.akshayejh.firebasesearch;



public class RecipeObject {

    public String name, image, execution, ingredients;

    public RecipeObject(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getexecution() {
        return execution;
    }
    public void setexecution(String execution) {
        this.execution = execution;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public RecipeObject(String name, String image, String execution) {
        this.name = name;
        this.image = image;
        this.execution = execution;
        this.ingredients = ingredients;
    }
}
