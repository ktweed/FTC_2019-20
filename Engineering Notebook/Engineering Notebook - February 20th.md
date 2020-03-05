# Engineering Notebook - February 20th
### Members Present:
Amog, Keith

### Objectives:
Promote Lillian Osborne's robotics project at the open house

### Completed Tasks:
We torture-tested our robot by letting people drive it around during our open house

### Reflections
We discovered a few issues with our robot. First, the gripping mechanism the other team designed is very hard on the parts. It bent the c-channel, so it was very hard to get blocks between the grippers. Second, the motor does not have a very good encoder, and if it drops out, the motor will not stop turning. This torqued our metal so hard that it actually bent the axle.

### Details, Diagrams, and Images
We will need to rebuild the axle, as well as determine how to prevent this from happening again. My main idea is to have a check in the code to see if the encoder stops responding or if the value freezes, and if so, to halt the motor to prevent damage. An easier way could be to simply have a button to kill the motor.