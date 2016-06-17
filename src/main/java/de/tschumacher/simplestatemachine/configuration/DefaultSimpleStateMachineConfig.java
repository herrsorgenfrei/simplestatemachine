/*
 * Copyright 2016 Tobias Schumacher
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.tschumacher.simplestatemachine.configuration;

import java.util.HashMap;
import java.util.Map;

import de.tschumacher.simplestatemachine.configuration.state.DefaultStateConfiguration;
import de.tschumacher.simplestatemachine.configuration.state.StateConfiguration;

public class DefaultSimpleStateMachineConfig<State> implements SimpleStateMachineConfig<State> {
  Map<State, StateConfiguration<State>> configuration =
      new HashMap<State, StateConfiguration<State>>();


  @Override
  public StateConfiguration<State> configure(State state) {
    if (fetch(state) != null)
      return fetch(state);

    final StateConfiguration<State> stateConfig = new DefaultStateConfiguration<State>();
    this.configuration.put(state, stateConfig);
    return stateConfig;
  }


  @Override
  public StateConfiguration<State> fetch(State state) {
    return this.configuration.get(state);
  }

}
