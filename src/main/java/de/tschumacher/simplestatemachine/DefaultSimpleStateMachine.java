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
package de.tschumacher.simplestatemachine;

import de.tschumacher.simplestatemachine.configuration.SimpleStateMachineConfig;
import de.tschumacher.simplestatemachine.configuration.state.StateConfiguration;
import de.tschumacher.simplestatemachine.exception.TransitionNotAllowedException;
import de.tschumacher.simplestatemachine.handler.StateChangeHandler;

public class DefaultSimpleStateMachine<State, Context, ContextService> implements
    SimpleStateMachine<State, Context, ContextService> {

  private final SimpleStateMachineConfig<State, Context, ContextService> config;
  private State actualState;


  public DefaultSimpleStateMachine(SimpleStateMachineConfig<State, Context, ContextService> config,
      State actualState) {
    super();
    this.config = config;
    this.actualState = actualState;
  }


  @Override
  public Context change(State newState, Context context, ContextService contextService) {
    final StateConfiguration<State, Context, ContextService> actualStateConfig =
        this.config.fetch(this.actualState);
    if (actualStateConfig == null || !actualStateConfig.transitionAllowed(newState))
      throw new TransitionNotAllowedException();

    this.actualState = newState;
    final StateChangeHandler<Context, ContextService> handler = actualStateConfig.handler(newState);
    if (handler != null)
      return handler.handle(context, contextService);

    return context;
  }


  @Override
  public Context change(State newState) {
    return change(newState, null, null);
  }


  @Override
  public Context change(State newState, Context context) {
    return change(newState, context, null);
  }

  @Override
  public boolean transitionAllowed(State newState) {
    final StateConfiguration<State, Context, ContextService> actualStateConfig =
        this.config.fetch(this.actualState);
    return actualStateConfig != null && actualStateConfig.transitionAllowed(newState);
  }
}
