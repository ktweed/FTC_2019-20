# Engineering Notebook - January 16th
### Members Present:
Amog, Aron, Keith

### Objectives:
Fix our motor controller issues

### Completed Tasks:
We were able to resolve the motor controller issues

### Reflections
What I *think* the cause was, is that 2 of the motor controllers had the same name. Even though they are never referenced in our program by these names, it seems that this breaks connectivity with the duplicate.

### Details, Diagrams, and Images
We tried replacing the core power distribution module, and the motor controller as well as all of the cables, and tried repinning the motor controllers. When reconfiguring, I noticed an error message about duplicate names, so I changed that and it still did not work, but there were no errors.

I then tried putting back the original core power distribution module, and the robot then worked.