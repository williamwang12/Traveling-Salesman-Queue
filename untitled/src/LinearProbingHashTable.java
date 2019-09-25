public class LinearProbingHashTable<TKey, TValue> {
    private int size;

    private TKey[] keys;
    private TValue[] values;

    public LinearProbingHashTable(int initialCapacity) {
        keys = (TKey[])new Object[initialCapacity];
        values = (TValue[])new Object[initialCapacity];
        size = 0;
    }

    public void put(TKey key, TValue value) {

        if (size >= keys.length / 2){
            resize(keys.length * 2);
        }

        int newIndex = Math.abs(key.hashCode()) % (keys.length);

        if (keys[newIndex] != null){
            boolean found = false;

            for (int a = 0; a < keys.length; a++){
                int current = (a + newIndex) % keys.length;
                if (keys[current] == null){
                    keys[current] = key;
                    values[current] = value;
                    size++;
                    return;
                }
            }

            //while (!found){
            //    if (a >= keys.length - 1){
            //        break;
            //    }
            //    else if (keys[a++] == null){
            //        found = true;
            //    }
            //    newIndex = a;
            //}

        }

        keys[newIndex] = key;
        values[newIndex] = value;
        size++;

    }

    public TValue get(TKey key) {

        for (int a = 0; a < keys.length; a++) {
            int currentIndex = (Math.abs(key.hashCode()) + a) % keys.length;
            if (key.equals(keys[currentIndex]) ) {
                return values[currentIndex];
            }
        }

        return null;

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size <= 0){
            return true;
        }
        return false;
    }
    /*
    public int hash(Tkey key){
        return Math.abs(key.hashcode() % (keys.length-1));
    }
    */

    private void resize(int newCapacity) {
        TKey[] tempKeys = keys;
        TValue[] tempValues = values;
        this.size = 0;
        keys = (TKey[])new Object[newCapacity];
        values = (TValue[])new Object[newCapacity];

        for (int i = 0; i < tempKeys.length; i++){
            if (tempKeys[i] != null){
                put(tempKeys[i],tempValues[i]);
            }
        }

    }
}