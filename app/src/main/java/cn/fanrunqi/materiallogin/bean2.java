package cn.fanrunqi.materiallogin;

public class bean2 {
    public int getImage1() {
        return Image1;
    }

    public void setImage1(int image1) {
        Image1 = image1;
    }

    public int getImage2() {
        return Image2;
    }

    public void setImage2(int image2) {
        Image2 = image2;
    }

    public int getImage3() {
        return Image3;
    }

    public void setImage3(int image3) {
        Image3 = image3;
    }

    public String getOffername1() {
        return offername1;
    }

    public void setOffername1(String offername1) {
        this.offername1 = offername1;
    }

    public String getValidupto1() {
        return validupto1;
    }

    public void setValidupto1(String validupto1) {
        this.validupto1 = validupto1;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    int Image1,Image2,Image3;
   String offername1,validupto1,description1;

    public bean2(int image1, int image2, int image3, String offername1, String validupto1, String description1) {
        Image1 = image1;
        Image2 = image2;
        Image3 = image3;
        this.offername1 = offername1;
        this.validupto1 = validupto1;
        this.description1 = description1;
    }
}
