/*
    Alicorn Systems GH6 Project "Narwhal"

    Copyright (c) Brandon Sanders [brandon@alicorn.io], Joshua Gagen [joshuagagen@outlook.com],
                  Edwin Munguia [daiokaio@gmail.com] and Justin Stone

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
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
