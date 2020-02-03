# 192. Word Frequency
# https://leetcode-cn.com/problems/word-frequency/

# Read from the file words.txt and output the word frequency list to stdout.

function solution() {
tr -s ' ' '\n' < words.txt |
sort |
uniq -c |
sort -r |
awk '{print $2" "$1}'
}

# prepare test file
echo 'the day is sunny the the
the sunny is is' > words.txt

echo $(solution)

rm words.txt
