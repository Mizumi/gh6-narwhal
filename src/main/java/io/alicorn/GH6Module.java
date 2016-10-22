/*
 * Project: gh6
 * Since: Oct 21, 2016
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
package io.alicorn;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Add all dependencies here that are implementations of interfaces or need
 * pre configuration.
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Module
public class GH6Module {

    @Provides @Singleton
    public Config provideConfig() {
        return new ConfigImpl();
    }
}
