/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusanalyser;

/**
 *
 * @author Gangani
 */
public class Virus {
    private final String vName;
    private final String dateCreated;
    private final String vType;
    private final String engine;
    private final String awarenes;
    private final String md5;

    public Virus(String vName, String md5, String dateCreated, String engine, String awarenes, String vType) {
        this.vName = vName;
        this.dateCreated = dateCreated;
        this.vType = vType;
        this.engine = engine;
        this.awarenes = awarenes;
        this.md5 = md5;
    }

    public String getVName() {
        return vName;
    }

    public String getDateCreated() {
        return dateCreated;
    }
    
    public String getVType() {
        return vType;
    }
        
    public String getMd5() {
        return md5;
    }

    public String getEngine() {
        return engine;
    }

    public String getAwarenes() {
        return awarenes;
    }
}
