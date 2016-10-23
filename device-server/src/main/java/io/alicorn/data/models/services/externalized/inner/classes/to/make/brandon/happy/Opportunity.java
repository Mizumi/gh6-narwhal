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
package io.alicorn.data.models.services.externalized.inner.classes.to.make.brandon.happy;

class Opportunity {
    private String skillsRequired;
    private String skillsDesired;
    private String requiredAvailability;
    private String startingRate;
    private String description;
    private String applicationInformation;
    private String expiration;

    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public String getSkillsDesired() {
        return skillsDesired;
    }

    public void setSkillsDesired(String skillsDesired) {
        this.skillsDesired = skillsDesired;
    }

    public String getRequiredAvailability() {
        return requiredAvailability;
    }

    public void setRequiredAvailability(String requiredAvailability) {
        this.requiredAvailability = requiredAvailability;
    }

    public String getStartingRate() {
        return startingRate;
    }

    public void setStartingRate(String startingRate) {
        this.startingRate = startingRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplicationInformation() {
        return applicationInformation;
    }

    public void setApplicationInformation(String applicationInformation) {
        this.applicationInformation = applicationInformation;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}