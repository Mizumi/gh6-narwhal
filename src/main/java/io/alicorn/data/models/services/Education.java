package io.alicorn.data.models.services;


import java.util.List;

public class Education extends Service {
    private List<Discipline> disciplines;

    public Education() {
        super(ServiceType.Education);
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    private class Discipline {
        private String type;
        private String cost;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }
    }

}
