/*
 * Copyright (c) 2011-2021 VMware Inc. or its affiliates, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package reactor.kotlin.extra.swing

import reactor.core.scheduler.Scheduler
import org.eclipse.swt.widgets.Display
import reactor.swing.SwtScheduler

/**
 * Extension to convert a SWT Display to a [Scheduler].
 *
 * @author Simon Baslé
 * @since 3.1.1
 * @deprecated To be removed exceptionally fast in 3.5.0. See https://github.com/reactor/reactor-addons/issues/273
 */
@Deprecated(message = "To be removed exceptionally fast in 3.5.0. SWT support is being removed in addons",
    level = DeprecationLevel.ERROR)
fun Display.toScheduler(): Scheduler = SwtScheduler.from(this)