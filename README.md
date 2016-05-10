**Votr - Your voting assistant [CMSC436]**

Features include: 
- Direction to closest polling location
- Getting closest election date
- Short information of candidates 
- Most recent Tweets relating to a candidate. 

The most recent tweet function is backed by machine learning and real time
sentiment analysis. The classifier is trained using the Lingpipe library. Approximately 15 tweets are fetched at a given time, and based on the 15 tweets, the approval rating of a candidate is evaluated by taking the average of the candidate's score. 

Since 90% of media share is controlled by less than 10 corporations, our app serves an important role of getting the public's genuine opinion more accessible to the users. Our voter rating is also completely unbiased because it is purely based on the sentiment of the text. 

Libraries/API used:
Google Civic Information,
OpenFEC,
Twitter (Twitter4j),
Sentiment Analysis Library (Lingpipe).


Presentation: https://docs.google.com/presentation/d/1l1XfCg4ZVAcW7D9VPafhfJWXd6KkAGGfW1GyUlXY_tk/edit#slide=id.g11222ff36a_0_31


Authors: Eric Tung, George Cleary, Han Huang, Fahed HijaziDan.

