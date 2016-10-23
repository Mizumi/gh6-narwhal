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

public class Shelter extends Service {
    private int totalAvailability;
    private int currentAvailability;
    private String unitType;

    public Shelter() {
        super(ServiceType.Shelter);
    }

    public int getTotalAvailability() {
        return totalAvailability;
    }

    public void setTotalAvailability(int totalAvailability) {
        this.totalAvailability = totalAvailability;
    }

    public int getCurrentAvailability() {
        return currentAvailability;
    }

    public void setCurrentAvailability(int currentAvailability) {
        this.currentAvailability = currentAvailability;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}
