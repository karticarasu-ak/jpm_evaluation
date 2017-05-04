# jpm_evaluation
jpm_evaluation

### Basic classes and its functions ###

#ProcessInstructionMainTest : The main class to test the project

#ProcessInstruction : The interface which handles different actions on a intruction  like adding new instrution 
                     showing report etc
  
#PerformDataValidation: This Interface performs validation on the Instruction Data entered.
                       * The main purpose is
                       * verify the different units on basic checks
                       * verify the settlement date.
                       * verify the instruction date.
#PerformDataValidationTest : Performs Test on PerformDataValidation class

#InstructionData: This class holds all the instruction data for a particular day 
                 * When a new instruction is added total buy sum and total sell Sum is calculated along 
                   with ranks for that day

#InstructionDataList : This class holds all the instruction data for a particular day 
                     * When a new instruction is added "total buy sum" and "total sell Sum" is calculated along 
                       with ranks for that day
#InstructionDataListTest : Performs tests on InstructionDataList         
                      
                      
 #Note: Any particular design pattern is not followed as such. But all the classes are desingned in such a way that 
 its would be easy to incorporate them in design pattern.
                      
