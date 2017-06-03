package raviwan.trongtum.ponpitoon.msccheck;

/**
 * Created by User on 1/6/2560.
 */

public class MyConstant {

    //For Spinner
    private String[] titleStrings = new String[]{"Mr", "Miss", "Mrs"};
    private String[] yearStrings = new String[]{"1", "2", "3", "4"};
    private String[] majorStrings = new String[]{"Major A", "Major B", "Major C", "Major D"};
    private String[] classStrings = new String[]{"Class A", "Class B", "Class C"};

    //For URL
    private String urlAddUserFarString = "http://swiftcodingthai.com/dom/addUserFar.php";
    private String urlImageString = "http://swiftcodingthai.com/dom/Image";
    private String urlGetUserString = "http://swiftcodingthai.com/dom/getUserDom.php";
    private String urlGetTeacherString = "http://swiftcodingthai.com/dom/getTeacher.php";

    private String[] LoginStrings = new String[]{"id",
            "Image",
            "StudentID",
            "Title",
            "Name",
            "Year",
            "User",
            "Password",
            "Major",
            "Class",
            "Phone",
            "Email",
            "Status"};


    public String getUrlGetTeacherString() {
        return urlGetTeacherString;
    }

    public String[] getLoginStrings() {
        return LoginStrings;
    }

    public String getUrlGetUserString() {
        return urlGetUserString;
    }

    public String getUrlImageString() {
        return urlImageString;
    }

    public String getUrlAddUserFarString() {
        return urlAddUserFarString;
    }

    public String[] getTitleStrings() {
        return titleStrings;
    }

    public String[] getYearStrings() {
        return yearStrings;
    }

    public String[] getMajorStrings() {
        return majorStrings;
    }

    public String[] getClassStrings() {
        return classStrings;
    }
}   //Main Class
