package com.zcj.mreader.base;

import java.util.List;

public class BaseGankBean<T>{

    /**
     * error : false
     * results : [{}]
     */

    private boolean error;
    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

}
