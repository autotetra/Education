#!/usr/bin/env python3

from mrjob.job import MRJob
from mrjob.step import MRStep
import csv

class DistinctCommentators(MRJob):

    def steps(self):
        return [
            MRStep(mapper=self.mapper_get_comments,
                   reducer=self.reducer_count_comments),
            MRStep(reducer=self.reducer_list_commentators)
        ]

    def mapper_get_comments(self, _, line):
        # Parse the line using csv
        reader = csv.reader([line])
        for row in reader:
            post_author, comment_author, _ = row
            yield (post_author, comment_author), None

    def reducer_count_comments(self, author_pair, _):
        post_author, comment_author = author_pair
        yield post_author, comment_author

    def reducer_list_commentators(self, post_author, commentators):
        unique_commentators = set(commentators)
        yield post_author, (len(unique_commentators), list(unique_commentators))

if __name__ == '__main__':
    DistinctCommentators.run()
