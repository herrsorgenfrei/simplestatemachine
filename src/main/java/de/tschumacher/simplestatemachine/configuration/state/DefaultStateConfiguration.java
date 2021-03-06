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
package de.tschumacher.simplestatemachine.configuration.state;

import java.util.HashMap;
import java.util.Map;

import de.tschumacher.simplestatemachine.handler.StateChangeHandler;

public class DefaultStateConfiguration<State, Context, ContextService> implements
    StateConfiguration<State, Context, ContextService> {

  Map<State, StateChangeHandler<Context, ContextService>> stateConfigurations =
      new HashMap<State, StateChangeHandler<Context, ContextService>>();

  @Override
  public StateConfiguration<State, Context, ContextService> permit(State state,
      StateChangeHandler<Context, ContextService> handler) {
    this.stateConfigurations.put(state, handler);
    return this;
  }

  @Override
  public StateConfiguration<State, Context, ContextService> permit(State state) {
    return permit(state, null);
  }

  @Override
  public StateChangeHandler<Context, ContextService> handler(State state) {
    return this.stateConfigurations.get(state);
  }

  @Override
  public boolean transitionAllowed(State state) {
    return this.stateConfigurations.containsKey(state);
  }

}
