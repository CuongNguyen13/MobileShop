package BeanProduct;

public class StartEvaluate {
    private String idProduct;
    private int star1;
    private int star2;
    private int star3;
    private int star4;
    private int star5;

    public StartEvaluate() {
    }

    public StartEvaluate(String idProduct, int star1, int star2, int star3, int star4, int star5) {
        this.idProduct = idProduct;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.star5 = star5;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public int getStar1() {
        return star1;
    }

    public void setStar1(int star1) {
        this.star1 = star1;
    }

    public int getStar2() {
        return star2;
    }

    public void setStar2(int star2) {
        this.star2 = star2;
    }

    public int getStar3() {
        return star3;
    }

    public void setStar3(int star3) {
        this.star3 = star3;
    }

    public int getStar4() {
        return star4;
    }

    public void setStar4(int star4) {
        this.star4 = star4;
    }

    public int getStar5() {
        return star5;
    }

    public void setStar5(int star5) {
        this.star5 = star5;
    }

    @Override
    public String toString() {
        return "StartEvaluate{" +
                "idProduct='" + idProduct + '\'' +
                ", star1=" + star1 +
                ", star2=" + star2 +
                ", star3=" + star3 +
                ", star4=" + star4 +
                ", star5=" + star5 +
                '}';
    }

    public double avgStar(){
        return 0;
    }

}