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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import de.tschumacher.simplestatemachine.SimpleStateMachine;
import de.tschumacher.simplestatemachine.configuration.state.StateConfiguration;
import de.tschumacher.simplestatemachine.state.StringService;
import de.tschumacher.simplestatemachine.state.TestState;

public class SimpleStateMachineConfigTest {
  private SimpleStateMachineConfig<TestState, String, StringService> service = null;

  @Before
  public void setUp() {
    this.service = new DefaultSimpleStateMachineConfig<TestState, String, StringService>();
  }

  @Test
  public void configureNotNullTest() {
    final TestState state = TestState.A;
    final StateConfiguration<TestState, String, StringService> configure =
        this.service.configure(state);

    assertNotNull(configure);
  }

  @Test
  public void configurationAreEqualTest() {
    final TestState state = TestState.A;
    final StateConfiguration<TestState, String, StringService> configure =
        this.service.configure(state);
    final StateConfiguration<TestState, String, StringService> fetch = this.service.fetch(state);
    assertEquals(configure, fetch);
  }


  @Test
  public void fetchNotConfiguredTest() {
    final TestState state = TestState.A;
    final StateConfiguration<TestState, String, StringService> fetch = this.service.fetch(state);
    assertNull(fetch);
  }


  @Test
  public void createMachineTest() {
    final TestState state = TestState.A;
    final SimpleStateMachine<TestState, String, StringService> machine =
        this.service.createMachine(state);
    assertNotNull(machine);
  }
}
