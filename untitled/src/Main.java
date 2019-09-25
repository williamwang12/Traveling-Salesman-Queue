public class Main {
    public static Integer[] runTest()
    {
        class BadInt {
            private final int value;

            BadInt(int value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object other) {
                if (!(other instanceof BadInt)) {
                    return false;
                }
                BadInt that = (BadInt) other;
                return this.value == that.value;
            }

            @Override
            public int hashCode() {
                // Technically valid, but terrible choice of hash code.
                return -42;
            }

            @Override
            public String toString() {
                return String.valueOf(value);
            }
        }

        LinearProbingHashTable<BadInt, Integer> table = new LinearProbingHashTable<BadInt, Integer>(4);
        for (int i = 0; i < 10; ++i) {
            table.put(new BadInt(i), i);
        }

        Integer[] results = new Integer[table.size()];
        for (int i = 0; i < 10; ++i) {
            results[i] = table.get(new BadInt(i));
        }
        return results;
    }

    public static void main(String[] args)
    {
        Integer[] returnVal = runTest();
    }
}
