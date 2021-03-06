/* ###
 * IP: GHIDRA
 * REVIEWED: YES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.util.constraint;

import generic.constraint.DecisionTree;
import ghidra.program.model.listing.Program;
import ghidra.util.Msg;
import ghidra.util.classfinder.ClassSearcher;

import java.util.Set;

public class ProgramDecisionTree extends DecisionTree<Program> {
	public ProgramDecisionTree() {
		super();

		Set<Class<? extends ProgramConstraint>> classes =
			ClassSearcher.getClasses(ProgramConstraint.class);
		for (Class<? extends ProgramConstraint> constraintClass : classes) {
			try {
				ProgramConstraint contraint = constraintClass.newInstance();
				registerConstraintType(contraint.getName(), constraintClass);
			}
			catch (Exception e) {
				Msg.error(this,
					"Can't create constraint instance for " + constraintClass.getName(), e);
			}
		}
	}
}
