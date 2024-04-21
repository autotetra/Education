from mrjob.job import MRJob
import csv
from datetime import datetime

class PostAuthorComments(MRJob):
    def mapper(self, _, line):
        # Parse the line using csv
        reader = csv.reader([line])
        for row in reader:
            post_author, comment_author, comment_date = row
            yield post_author, (comment_author, comment_date)

    def reducer(self, key, values):
        comments = list(values)
        comments.sort(key=lambda x: datetime.strptime(x[1], "%Y-%m-%d"), reverse=True)
        yield key, (len(comments), comments)

if __name__ == '__main__':
    PostAuthorComments.run()
