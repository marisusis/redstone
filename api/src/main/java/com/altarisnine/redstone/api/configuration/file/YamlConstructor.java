package com.altarisnine.redstone.api.configuration.file;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

public class YamlConstructor extends Constructor {
    public YamlConstructor() {
        this.yamlConstructors.put(Tag.MAP, new ConstructConfigObject());
    }

    private class ConstructConfigObject extends SafeConstructor.ConstructYamlMap {
        @Override
        public Object construct(Node node) {
            if (node.isTwoStepsConstruction()) {
                throw new YAMLException("the fuck? with node: " + node);
            }

            return super.construct(node);
        }

        @Override
        public void construct2ndStep(Node node, Object object) {
            throw new YAMLException("the heck be goin on with node: " + node);
        }
    }
}
