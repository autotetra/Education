#!/usr/bin/env python3

from mrjob.job import MRJob
import csv
from datetime import datetime

class CommentAuthorActivity(MRJob):
    def mapper(self, _, line):
        # Parse the line using csv
        reader = csv.reader([line])
        for row in reader:
            post_author, comment_author, comment_date = row
            yield comment_author, (post_author, comment_date)

    def reducer(self, key, values):
        # Sort comments by date in descending order
        sorted_comments = sorted(values, key=lambda x: datetime.strptime(x[1], "%Y-%m-%d"), reverse=True)
        yield key, (len(sorted_comments), list(sorted_comments))

if __name__ == '__main__':
    CommentAuthorActivity.run()
