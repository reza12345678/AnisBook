package android.app.anisbookupdate.Database;

public class Container_Model {
    int pa_id;
    String contain;
    String translate;
    int tblAnis_id;

    public Container_Model(int pa_id, String contain, String translate, int tblAnis_id) {
        this.pa_id = pa_id;
        this.contain = contain;
        this.translate = translate;
        this.tblAnis_id = tblAnis_id;
    }

    public int getPa_id() {
        return pa_id;
    }

    public void setPa_id(int pa_id) {
        this.pa_id = pa_id;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public int getTblAnis_id() {
        return tblAnis_id;
    }

    public void setTblAnis_id(int tblAnis_id) {
        this.tblAnis_id = tblAnis_id;
    }
}
