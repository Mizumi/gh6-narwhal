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

import io.alicorn.data.models.Continuum;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Iterator;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Singleton
public class CocDbFacade {

    public static final String COC_COLLECTION = "Cocs";

    @Inject
    public CocDbFacade() { }

    public void setCoc(Continuum continuum) {
        JongoDriver.getCollection(COC_COLLECTION).update("{uuid:#}", continuum.getUuid()).upsert().with(continuum);
    }

    public Continuum getContinuum(String uuid) {
        return JongoDriver.getCollection(COC_COLLECTION).findOne("{uuid:#}", uuid).as(Continuum.class);
    }

    public Iterator<Continuum> getAllContinuum() {
        return JongoDriver.getCollection(COC_COLLECTION).find().as(Continuum.class);
    }

    public void deleteContinuum(String uuid) {
        JongoDriver.getCollection(COC_COLLECTION).remove("{uuid:#}", uuid);
    }
}
