#!/usr/bin/env python3

from mrjob.job import MRJob
from mrjob.step import MRStep
import csv
from datetime import datetime


class PostAuthorComments(MRJob):

    def mapper(self, _, line):
        # Use csv library to correctly parse CSV lines, even if there are commas within fields
        reader = csv.reader([line])
        for row in reader:
            post_author, comment_author, comment_date = row
            yield post_author, (comment_author, comment_date)

    def reducer(self, key, values):
        # Sort comments by date descending and count them
        sorted_values = sorted(values, key=lambda x: datetime.strptime(
            x[1], '%Y-%m-%d'), reverse=True)
        num_comments = len(sorted_values)
        yield key, (num_comments, list(sorted_values))


if __name__ == '__main__':
    PostAuthorComments.run()
