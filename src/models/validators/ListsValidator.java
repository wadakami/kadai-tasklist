package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Tasks;


public class ListsValidator {
    public static List<String> validate(Tasks m) {
    List<String> errors = new ArrayList<String>();

    String title_error = _validateTitle(m.getTitle());
        if(!title_error.equals("")) {
        errors.add(title_error);
    }

    String content_error = _validateContent(m.getContent());
    if(!content_error.equals("")) {
        errors.add(content_error);
    }
/*修正前
    String deadline_error = _validateDeadline(new SimpleDateFormat("yyyy-MM-dd").format(m.getDeadline()));
    if(!deadline_error.equals("")) {
        errors.add(deadline_error);
    }
*/
    if(m.getDeadline() == null) {
        errors.add("期日のフォーマットが正しくありません");
    }

    return errors;

}



private static String _validateTitle(String title) {
    if(title == null || title.equals("")) {
        return "タイトルを入力してください。";
    }
    return "";
}

private static String _validateContent(String content) {
    if(content == null || content.equals("")) {
        return "内容を入力してください。";
    }
    return "";

}
/*修正前
private static String _validateDeadline(String deadline) {
    if(deadline.equals("1900-01-01")) {
        return "期日を入力してください。";
    }

    return "";

}*/
}






