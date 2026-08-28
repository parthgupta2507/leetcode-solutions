class Solution:
    def lexPalindromicPermutation(self, s: str, target: str) -> str:
        n = len(s)

        # Count characters
        count = [0] * 26
        for ch in s:
            count[ord(ch) - ord('a')] += 1

        # A palindrome can have at most one odd-frequency character
        odd = []
        for i in range(26):
            if count[i] % 2:
                odd.append(i)

        if len(odd) > 1:
            return ""

        # Middle character
        middle = ""
        if odd:
            middle = chr(odd[0] + ord('a'))

        # Characters available for the left half
        half = [c // 2 for c in count]
        m = n // 2

        # Build the left half
        prefix = []

        def can_make_greater():
            # Put remaining characters in descending order.
            # This gives the largest possible completion.
            remaining = []

            for i in range(25, -1, -1):
                if half[i] > 0:
                    remaining.append(
                        chr(i + ord('a')) * half[i]
                    )

            left = ''.join(prefix) + ''.join(remaining)

            palindrome = left + middle + left[::-1]

            return palindrome > target

        for pos in range(m):
            found = False

            # Try smallest possible character first
            for c in range(26):

                if half[c] == 0:
                    continue

                # Choose this character
                half[c] -= 1
                prefix.append(chr(c + ord('a')))

                # Check whether some valid completion exists
                if can_make_greater():
                    found = True
                    break

                # Undo choice
                prefix.pop()
                half[c] += 1

            if not found:
                return ""

        # Construct final palindrome
        left = ''.join(prefix)
        answer = left + middle + left[::-1]

        if answer > target:
            return answer

        return ""