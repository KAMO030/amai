package com.kamo.amai;

import java.util.function.Consumer;
import java.util.function.Function;


public interface Any<Self extends Any> {

   default Self with(Consumer<Self> block){
      final Self self = (Self) this;
      block.accept(self);
      return self;
    }


    default<R> R map(Function<Self,R> block){
        final Self self = (Self) this;
        return block.apply(self);
    }
}
