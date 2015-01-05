K-Nearest-Neighbor
====================

K-Nearest-Neighbor for data set at http://archive.ics.uci.edu/ml/datasets/Car+Evaluation

Team
----
Franz Kuntke, Johannes Filter

Basic Idea
----------
1. Read Data from Files
2. Shuffle Data
3. Take first 2/3 of data as training data
4. Take last 1/3 as test data
5. Build up Classifier
6. Test against test data and save error

* Repeat Steps 2. to 6. and sum up error
* Check out the commented code for more details.

Evaluation
----------

* for k=1 the mean error is about 18%
* for k=5 the mean error is about 8%
* So the accuracy of k-Nearest-Neighbour with reasonable k is higher than Naive-Bayes classifier. Good values for k (in this example) are 3 or 5. 
* Our implementation of k-Nearest-Neighbour is much slower than our implementation of Naive-Bayes. This is caused by the eager approach of many distance calculations for k-Nearest-Neighbour.
