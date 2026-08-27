from collections import Counter

class Solution:
    def lexGreaterPermutation(self, s: str, target: str) -> str:
        n = len(s)
        total_counts = Counter(s)

        # Iterate from right to left to find the longest common prefix match length 'i'
        for i in range(n - 1, -1, -1):
            # Verify if the prefix target[:i] can be formed using s's characters
            prefix_counts = Counter(target[:i])
            
            # Check if target[:i] is a valid subset of s
            if any(prefix_counts[ch] > total_counts[ch] for ch in prefix_counts):
                continue

            # Remaining available characters after spending target[:i]
            rem_counts = total_counts - prefix_counts

            # Find the smallest character to place at index i that is > target[i]
            target_char = target[i]
            for char_code in range(ord(target_char) + 1, ord('z') + 1):
                c = chr(char_code)
                if rem_counts[c] > 0:
                    # Form prefix target[:i] + c
                    rem_counts[c] -= 1
                    
                    # Fill the rest (index i+1 to end) with the remaining characters sorted
                    suffix = "".join(sorted(rem_counts.elements()))
                    return target[:i] + c + suffix

        return ""