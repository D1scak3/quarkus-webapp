package org.acme.service;

import java.util.HashMap;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LabseqService {

    HashMap<Integer, Integer> cache;

    public LabseqService() {
        this.cache = new HashMap<>();
        this.cache.putAll(new HashMap<Integer, Integer>() {
            {
                put(0, 0);
                put(1, 1);
                put(2, 0);
                put(3, 1);
            }
        });
    }

    public int calcSeq(int value) {

        int res;

        if (isCached(value)) {
            return this.cache.get(value);
        } else {
            if (isCached(value - 4)) {
                int first = this.cache.get(value - 4);
                if (isCached(value - 3)) {
                    int second = this.cache.get(value - 3);
                    return first + second;
                }
                return first + first + 1;
            } else if (isCached(value - 3)) {
                int second = this.cache.get(value - 3);
                return second + second - 1;
            }
        }

        res = (value - 4) + (value - 3);

        this.cache.put(value, res);

        return res;
    }

    private boolean isCached(int value) {
        System.out.println("CHECKING IF CACHED...");
        boolean res = this.cache.containsKey(value);
        if (res) {
            System.out.println("CACHED!");
        } else {
            System.out.println("NOT CACHED!");
        }
        return res;
    }
}
