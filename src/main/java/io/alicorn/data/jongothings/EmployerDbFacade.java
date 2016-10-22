/*
 * Project: gh6
 * Since: Oct 22, 2016
 *
 * Copyright (c) Brandon Sanders [brandon@alicorn.io]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.alicorn.data.jongothings;

import io.alicorn.data.models.services.externalized.inner.classes.to.make.brandon.happy.Employer;

import javax.inject.Inject;
import java.util.Iterator;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
public class EmployerDbFacade {

    public static final String EMPLOYER_COLLECTION = "Employers";

    @Inject
    public EmployerDbFacade() { }

    public void setEmployer(Employer employer) {
        JongoDriver.getCollection(EMPLOYER_COLLECTION).update("{uuid:#}", employer.getUuid()).upsert().with(employer);
    }

    public Employer getEmployer(String uuid) {
        return JongoDriver.getCollection(EMPLOYER_COLLECTION).findOne("{uuid:#}", uuid).as(Employer.class);
    }

    public Iterator<Employer> getAllEmployers() {
        return JongoDriver.getCollection(EMPLOYER_COLLECTION).find().as(Employer.class);
    }

    public void deleteEmployer(String uuid) {
        JongoDriver.getCollection(EMPLOYER_COLLECTION).remove("{uuid:#}", uuid);
    }
}
