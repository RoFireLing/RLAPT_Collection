# A Reinforcement Learning-based Approach to Partition Testing (RLAPT)

### Subject Programs

* Lab  
  Includes 3 Java programs —— ACMS/CUBS/ERS  
  (source code, 「used」mutants, and test case generation guidelines)
* SIR  
  Includes 3 C programs —— GREP/GZIP/MAKE  
  (source code, bash/shell scripts, test cases, etc.)

### Partition

* For the programs in SIR, select **one category** and partition the input domain according to its choices;
* For the laboratory programs, randomly selected **two categories** and then partitioned their input domain according to
  the combinations of choices (the categories and corresponding choices of ACMS, CUBS and ERS are shown in 
  **Categories.pdf**).

### Strategy

* Specific implementation of each testing technique
    * RPT
    * DRT
    * D-DRT/DDRTKM
    * APT {MAPT & RAPT}
    * RLAPT {RLAPT_Q & RLAPT_S}

### Executor

* A demo for test execution

### Utils

* A series of **Functions for recording metrics**
* **Function for calculating /delta** for 「DRT」 and 「RAPT」
* **Function for K-Medoid Clustering**

</br>

#### ⏩ To get [Ubuntu](https://ubuntu.com/)

***

## Repositories List

1. ~~[RLAPT4SIR(unpublicized)](https://github.com/RoFireLing/RL-APT_Expand)~~
2. ~~[RLAPT4Lab(unpublicized)](https://github.com/RoFireLing/RL-APT)~~
3. ~~[Supplementary Experiment_D-DRT(unpublicized)](https://github.com/RoFireLing/RLAPT_DDRTKM)~~
4. [RLAPT_base-on-FDMT](https://github.com/RoFireLing/RL-APT_FDMTbased)
5. [Implementation4TestingTechniques](https://github.com/RoFireLing/RL-APT_Strategy)
