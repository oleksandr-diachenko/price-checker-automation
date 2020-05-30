package oleksandrdiachenko.pricechecker.helper;

import oleksandrdiachenko.pricechecker.pageobject.AbstractPage;

import java.util.function.Function;

public interface ExpectPageCondition<T extends AbstractPage, R> extends Function<T, R> {
}
