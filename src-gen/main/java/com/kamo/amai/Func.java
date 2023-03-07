package com.kamo.amai;

import java.io.Serializable;

public interface Func<S extends Func>  extends Any<S> , Serializable {

    int arity();


}
