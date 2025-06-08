package lab9.common.dto;

public class CountryDto {
    
    private int id;
    private String name;
    private int code;
    private String continentName;  
    
    public CountryDto() {}
    
    public CountryDto(int id, String name, int code, String continentName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.continentName = continentName;
    }
    
    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    
    public int getCode() { 
        return code;
     }
    public void setCode(int code) { 
        this.code = code; 
    }
    
    public String getContinentName() { return continentName; }
    public void setContinentName(String continentName) { this.continentName = continentName; }
    
    @Override
    public String toString() {
        return "CountryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", continentName='" + continentName + '\'' +
                '}';
    }
}
