package in.tvac.akshayejh.firebasesearch;

public class AdvancedItem {

    private String mImageResource;
    private String mText1;

    public AdvancedItem(String imageResource, String text1, String text2) {
        mImageResource = imageResource;
        mText1 = text1;
    }

    public String getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

}